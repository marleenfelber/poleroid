CREATE TABLE move_alias (
    move_id BIGINT NOT NULL REFERENCES move(id) ON DELETE CASCADE,
    alias VARCHAR(100) NOT NULL,
    PRIMARY KEY (move_id, alias)
);