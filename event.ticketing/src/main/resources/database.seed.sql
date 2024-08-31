INSERT INTO categories (category_id, name)
VALUES
    (1, 'Concerts'),
    (2, 'Sports'),
    (3, 'Theater'),
    (4, 'Conferences'),
    (5, 'Workshops'),
    (6, 'Festivals'),
    (7, 'Comedy Shows'),
    (8, 'Exhibitions');

INSERT INTO events (event_id, name, description, seats, ticket_price)
VALUES
    (UUID(), 'Rock Concert', 'An amazing rock concert with live bands.', 500, 50),
    (UUID(), 'Tech Conference', 'A conference on the latest in tech innovations.', 300, 150),
    (UUID(), 'Stand-Up Comedy Night', 'An evening of laughter with top comedians.', 200, 30),
    (UUID(), 'Art Exhibition', 'Showcasing contemporary art from various artists.', 100, 20),
    (UUID(), 'Community Festival', 'A festival celebrating local culture and food.', 1000, 10),
    (UUID(), 'Jazz Night', 'A relaxing evening with live jazz music.', 150, 40),
    (UUID(), 'Food Truck Festival', 'A gathering of the best food trucks in town.', 800, 15),
    (UUID(), 'Startup Pitch Day', 'Pitch your startup ideas to investors.', 200, 100),
    (UUID(), 'Wine Tasting Experience', 'Sample exquisite wines from local vineyards.', 120, 60),
    (UUID(), 'Marathon 2024', 'Join the city marathon and test your endurance.', 5000, 25),
    (UUID(), 'Shakespeare Play', 'Experience a classic performance of Hamlet.', 180, 45),
    (UUID(), 'Yoga Workshop', 'A peaceful yoga retreat in nature.', 50, 150),
    (UUID(), 'Gaming Conference', 'Compete in the ultimate e-sports tournament.', 400, 20),
    (UUID(), 'Spring Fashion Show', 'See the latest trends at the spring fashion show.', 300, 80);

INSERT INTO events_categories (event_id, category_id)
VALUES
    ((SELECT event_id FROM events WHERE name = 'Rock Concert'), 1),
    ((SELECT event_id FROM events WHERE name = 'Rock Concert'), 6),
    ((SELECT event_id FROM events WHERE name = 'Tech Conference'), 4),
    ((SELECT event_id FROM events WHERE name = 'Tech Conference'), 5),
    ((SELECT event_id FROM events WHERE name = 'Stand-Up Comedy Night'), 7),
    ((SELECT event_id FROM events WHERE name = 'Art Exhibition'), 8),
    ((SELECT event_id FROM events WHERE name = 'Community Festival'), 6),
    ((SELECT event_id FROM events WHERE name = 'Jazz Night'), 1),
    ((SELECT event_id FROM events WHERE name = 'Food Truck Festival'), 6),
    ((SELECT event_id FROM events WHERE name = 'Startup Pitch Day'), 4),
    ((SELECT event_id FROM events WHERE name = 'Wine Tasting Experience'), 8),
    ((SELECT event_id FROM events WHERE name = 'Marathon 2024'), 2),
    ((SELECT event_id FROM events WHERE name = 'Shakespeare Play'), 3),
    ((SELECT event_id FROM events WHERE name = 'Yoga Workshop'), 5),
    ((SELECT event_id FROM events WHERE name = 'Gaming Conference'), 4),
    ((SELECT event_id FROM events WHERE name = 'Gaming Conference'), 5),
    ((SELECT event_id FROM events WHERE name = 'Spring Fashion Show'), 8);