CREATE TABLE chat.users
(
    user_id           UUID PRIMARY KEY   NOT NULL,
    first_name        VARCHAR(64)        NOT NULL,
    last_name         VARCHAR(64)        NOT NULL,
    email             VARCHAR(64) UNIQUE NOT NULL,
    password          VARCHAR(64)        NOT NULL,
    role              VARCHAR(12)        NOT NULL,
    status            VARCHAR(12)        NOT NULL,
    verification_code VARCHAR(15),
    reset_token       VARCHAR(15)
);

CREATE TABLE messages
(
    message_id UUID PRIMARY KEY,
    sender     UUID NOT NULL,
    recipient  UUID NOT NULL, -- Can be a user ID, group ID, or "ALL"
    content    TEXT         NOT NULL,
    timestamp  TIMESTAMP WITH TIME ZONE DEFAULT now()
);

