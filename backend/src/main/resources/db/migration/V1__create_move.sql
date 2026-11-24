CREATE TABLE move (
    id              BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name            VARCHAR(100) NOT NULL UNIQUE,
    pole_mode       VARCHAR(20) NOT NULL CHECK (pole_mode IN ('SPINNING', 'STATIC')),
    level           VARCHAR(20) NOT NULL CHECK (level IN ('BEGINNER', 'INTERMEDIATE', 'ADVANCED')),
    description     TEXT
);

CREATE TABLE move_category (
    move_id         BIGINT NOT NULL REFERENCES move(id) ON DELETE CASCADE,
    category        VARCHAR(30) NOT NULL CHECK (category IN
                        ('SPIN', 'CLIMB', 'SIT', 'HOLD', 'HANG', 'INVERT', 'HANDSPRING', 'SHOULDER_MOUNT',
                        'DROP', 'FLIP', 'STRENGTH', 'FLEXY', 'FLOORWORK', 'TRANSITION', 'COMBO')),
    PRIMARY KEY (move_id, category)
);