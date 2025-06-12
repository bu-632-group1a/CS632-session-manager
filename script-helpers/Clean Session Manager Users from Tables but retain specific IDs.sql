SELECT * FROM bookmarks;

DELETE FROM bookmarks
WHERE user_id NOT IN ('6848808bd4eee7741b2c5dd0', '684b34fcfc077c5d3d767db1');

SELECT * FROM checkins;

DELETE FROM checkins
WHERE user_id NOT IN ('6848808bd4eee7741b2c5dd0', '684b34fcfc077c5d3d767db1');
