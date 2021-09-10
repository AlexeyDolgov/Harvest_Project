INSERT INTO user (user_id, first_name, last_name, email, password, active)
	VALUES (1, 'Урожай', 'Администратор', 'harvest.cn.ua@gmail.com', '$2a$08$cT9g4BvOcPOf6N.W4mvEZuXCi.OPrBk1WbYjJGvq98f7ibjy6u5eO', True);

INSERT INTO access_level (user_id, access_levels)
	VALUES (1, 'USER'), (1, 'ADMIN');