package org.training.springboot.movieland.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.training.springboot.movieland.dto.GenreDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("cachedGenreService")
public class CachedGenreService implements GenreService {

	private final GenreService delegateService;
	private final int refreshmentPeriodInHours;
	private List<GenreDto> cache;
	private final Semaphore semaphore;
	private final ScheduledExecutorService refresher;

	@Autowired
	public CachedGenreService(@Qualifier("defaultGenreService") GenreService delegateService,
			@Value("${config.cache.refreshment.period}") int refreshmentPeriodInHours) {
		this.delegateService = delegateService;
		this.refreshmentPeriodInHours = refreshmentPeriodInHours;
		semaphore = new Semaphore(1);
		refresher = Executors.newScheduledThreadPool(1);
	}

	@PostConstruct
	private void init() {
		refreshCache();
		refresher.scheduleWithFixedDelay(this::refreshCache, refreshmentPeriodInHours, refreshmentPeriodInHours,
				TimeUnit.HOURS);
	}

	private void refreshCache() {
		try {
			semaphore.acquire();
			cache = delegateService.findAll();
		} catch (InterruptedException e) {
			log.error("cache refresh failed");
			throw new CacheModificationException("cache refresh failed", e);
		} finally {
			semaphore.release();
		}
	}

	@Override
	public List<GenreDto> findAll() {
		try {
			semaphore.acquire();
			return new ArrayList<>(cache);
		} catch (InterruptedException e) {
			log.error("fetch of all genres failed");
			throw new CacheModificationException("fetch of all genres failed", e);
		} finally {
			semaphore.release();
		}
	}

	@Override
	public Optional<GenreDto> findById(Long id) {
		try {
			semaphore.acquire();
			return cache.stream().filter(dto -> dto.id().equals(id)).findAny();
		} catch (InterruptedException e) {
			log.error("fetch of one genre failed");
			throw new CacheModificationException("fetch of one genre failed", e);
		} finally {
			semaphore.release();
		}
	}

	@Override
	public void save(GenreDto dto) {
		try {
			semaphore.acquire();
			cache.add(dto);
			delegateService.save(dto);
		} catch (InterruptedException e) {
			log.error("new genre addition failed");
			throw new CacheModificationException("new genre addition failed", e);
		} finally {
			semaphore.release();
		}
	}

	@Override
	public void save(Long id, GenreDto dto) {
		try {
			semaphore.acquire();
			if (cache.contains(dto)) {
				cache.set(cache.indexOf(dto), dto);
			}
			delegateService.save(id, dto);
		} catch (InterruptedException e) {
			log.error("entity update failed");
			throw new CacheModificationException("entity update failed", e);
		} finally {
			semaphore.release();
		}
	}

	@Override
	public void deleteById(Long id) {
		try {
			semaphore.acquire();
			for (var i = cache.iterator(); i.hasNext();) {
				if (i.next().id().equals(id)) {
					i.remove();
					break;
				}
			}
			delegateService.deleteById(id);
		} catch (InterruptedException e) {
			log.error("genre deletion failed");
			throw new CacheModificationException("genre deletion failed", e);
		} finally {
			semaphore.release();
		}
	}

}
