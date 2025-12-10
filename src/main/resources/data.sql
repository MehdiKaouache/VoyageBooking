-- == AEROPORTS =============================================================
        INSERT INTO aeroport (id, code, ville) VALUES
        (1, 'YUL', 'Montréal'),
        (2, 'YYZ', 'Toronto'),
        (3, 'CDG', 'Paris'),
        (4, 'YVR', 'Vancouver'),
        (5, 'JFK', 'New York');

        -- === OPERATEURS (compagnies) ==============================================
INSERT INTO operateur (id, code, nom) VALUES
                                          (1, 'AC',  'Air Canada'),
                                          (2, 'AF',  'Air France'),
                                          (3, 'WS',  'WestJet');

-- === VOLS (trajets "types") ===============================================
-- duree = minutes (double)
INSERT INTO vol (id, numero, origine_id, destination_id, duree) VALUES
                                                                    (101, 'AC870', 1, 3, 420),  -- Montréal (YUL) -> Paris (CDG) ~7h
                                                                    (102, 'AC401', 1, 2, 80 ),  -- Montréal (YUL) -> Toronto (YYZ)
                                                                    (103, 'WS123', 2, 4, 300),  -- Toronto (YYZ)  -> Vancouver (YVR)
                                                                    (104, 'AF344', 3, 1, 435),  -- Paris (CDG)    -> Montréal (YUL)
                                                                    (105, 'AC765', 1, 5, 75 );  -- Montréal (YUL) -> New York (JFK)

-- === OFFRES (vols planifiés par opérateur, avec date/heure et prix) =======
-- depart est une chaîne (VARCHAR) dans ton entité: ISO-8601 pour faire propre
INSERT INTO offre (id, trajet_id, operateur_id, depart,      prix_base) VALUES
                                                                            (1001, 101, 1, '2025-12-15T19:30',  850.00),  -- AC opère AC870 (YUL->CDG)
                                                                            (1002, 104, 2, '2025-12-22T13:10',  890.00),  -- AF opère AF344 (CDG->YUL)
                                                                            (1003, 102, 1, '2025-12-10T08:15',  120.00),  -- AC401 (YUL->YYZ)
                                                                            (1004, 103, 3, '2025-12-11T09:45',  320.00),  -- WS123 (YYZ->YVR)
                                                                            (1005, 105, 1, '2025-12-12T07:00',  150.00);  -- AC765 (YUL->JFK)