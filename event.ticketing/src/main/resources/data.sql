-- Insert categories only if they do not already exist
INSERT INTO categories (category_id, name)
VALUES (1, 'Concerts')
ON DUPLICATE KEY UPDATE name = name;

INSERT INTO categories (category_id, name)
VALUES (2, 'Sports')
ON DUPLICATE KEY UPDATE name = name;

INSERT INTO categories (category_id, name)
VALUES (3, 'Theater')
ON DUPLICATE KEY UPDATE name = name;

INSERT INTO categories (category_id, name)
VALUES (4, 'Conferences')
ON DUPLICATE KEY UPDATE name = name;

INSERT INTO categories (category_id, name)
VALUES (5, 'Workshops')
ON DUPLICATE KEY UPDATE name = name;

INSERT INTO categories (category_id, name)
VALUES (6, 'Festivals')
ON DUPLICATE KEY UPDATE name = name;

INSERT INTO categories (category_id, name)
VALUES (7, 'Comedy Shows')
ON DUPLICATE KEY UPDATE name = name;

INSERT INTO categories (category_id, name)
VALUES (8, 'Exhibitions')
ON DUPLICATE KEY UPDATE name = name;

INSERT INTO events (event_id, name, description, seats, ticket_price)
SELECT UUID(), 'Rock Concert', 'An amazing rock concert with live bands.', 500, 50
WHERE NOT EXISTS (SELECT 1 FROM events WHERE name = 'Rock Concert');

INSERT INTO events (event_id, name, description, seats, ticket_price)
SELECT UUID(), 'Tech Conference', 'A conference on the latest in tech innovations.', 300, 150
WHERE NOT EXISTS (SELECT 1 FROM events WHERE name = 'Tech Conference');

INSERT INTO events (event_id, name, description, seats, ticket_price)
SELECT UUID(), 'Stand-Up Comedy Night', 'An evening of laughter with top comedians.', 200, 30
WHERE NOT EXISTS (SELECT 1 FROM events WHERE name = 'Stand-Up Comedy Night');

INSERT INTO events_categories (event_id, category_id)
SELECT (SELECT event_id FROM events WHERE name = 'Rock Concert'), 1
WHERE NOT EXISTS (
    SELECT 1 FROM events_categories
    WHERE event_id = (SELECT event_id FROM events WHERE name = 'Rock Concert')
    AND category_id = 1
);

INSERT INTO events_categories (event_id, category_id)
SELECT (SELECT event_id FROM events WHERE name = 'Rock Concert'), 6
WHERE NOT EXISTS (
    SELECT 1 FROM events_categories
    WHERE event_id = (SELECT event_id FROM events WHERE name = 'Rock Concert')
    AND category_id = 6
);

INSERT INTO events_categories (event_id, category_id)
SELECT (SELECT event_id FROM events WHERE name = 'Tech Conference'), 4
WHERE NOT EXISTS (
    SELECT 1 FROM events_categories
    WHERE event_id = (SELECT event_id FROM events WHERE name = 'Tech Conference')
    AND category_id = 4
);

INSERT INTO events_categories (event_id, category_id)
SELECT (SELECT event_id FROM events WHERE name = 'Tech Conference'), 5
WHERE NOT EXISTS (
    SELECT 1 FROM events_categories
    WHERE event_id = (SELECT event_id FROM events WHERE name = 'Tech Conference')
    AND category_id = 5
);

INSERT INTO events_categories (event_id, category_id)
SELECT (SELECT event_id FROM events WHERE name = 'Stand-Up Comedy Night'), 7
WHERE NOT EXISTS (
    SELECT 1 FROM events_categories
    WHERE event_id = (SELECT event_id FROM events WHERE name = 'Stand-Up Comedy Night')
    AND category_id = 7
);