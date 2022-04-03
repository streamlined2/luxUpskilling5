ALTER TABLE IF EXISTS movieland.movie ADD CONSTRAINT minimum_issue_year CHECK(issue_year >= 1895);
ALTER TABLE IF EXISTS movieland.movie ADD CONSTRAINT positive_rating CHECK(rating >= 0);
ALTER TABLE IF EXISTS movieland.movie ADD CONSTRAINT positive_price CHECK(price >= 0);
