INSERT INTO player (id,name,lastname) VALUES
        (1,'Maria','Sanchez'),
        (2,'Federico','Perez');

INSERT INTO wallet(id, player_id ,cash_balance, bonus_balance)
VALUES
        (1, 1, 100, 100),
        (2, 2, 500, 300);

INSERT INTO transaction (id, transaction_id, player_id ,amount, type, proportional_bet)
VALUES
        (1, 1, 1, 100, 1, 0),
        (2, 2, 1, 50, 2, 0),
        (3, 3, 2, 300, 1, 0),
        (4, 4, 2, 100, 2, 0),
        (5, 5, 2, 100, 3, 1),
        (6, 6, 2, 200, 4, 0)
        ;
