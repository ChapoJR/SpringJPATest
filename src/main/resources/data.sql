DROP ALL OBJECTS;

CREATE TABLE article(
	id INT AUTO_INCREMENT,
	title VARCHAR(200),
	content VARCHAR(200)
);

CREATE TABLE tag(
    id INT AUTO_INCREMENT,
	article_id INT,
	tag VARCHAR(200)
);