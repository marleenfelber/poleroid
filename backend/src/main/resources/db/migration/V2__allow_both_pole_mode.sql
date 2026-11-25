ALTER TABLE move
    DROP CONSTRAINT move_pole_mode_check;

ALTER TABLE move
    ADD CONSTRAINT move_pole_mode_check
    CHECK (pole_mode IN ('SPINNING', 'STATIC', 'BOTH'));