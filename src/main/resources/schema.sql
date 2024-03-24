--DROP TABLE IF EXISTS "test";
--
--CREATE TABLE "test" (
--    "id" INT NOT NULL PRIMARY KEY,
--    "name" VARCHAR(255) NOT NULL
--);

DROP TABLE IF EXISTS "payments";
DROP TABLE IF EXISTS "users";

CREATE TABLE "users" (
    "id" BIGINT NOT NULL,
    "name" VARCHAR(255) NOT NULL,
    "email" VARCHAR(255) NOT NULL,
    "password" VARCHAR(255) NOT NULL
    CONSTRAINT "users_pk" PRIMARY KEY ("id")
);

CREATE TABLE "payments" (
    "id" BIGINT NOT NULL,
    "user_id" VARCHAR(255) NOT NULL,
    "friend_id" VARCHAR(255) NOT NULL,
    "amount" DECIMAL(10, 2) NOT NULL,
    "payment_date" TIMESTAMP NOT NULL,
    "description" VARCHAR(255) NOT NULL,
    "status" VARCHAR(255) NOT NULL,
    CONSTRAINT "payments_pk" PRIMARY KEY ("id"),
    CONSTRAINT "payments_fk" FOREIGN KEY ("user_id") REFERENCES "users" ("id")
    CONSTRAINT "payments_fk2" FOREIGN KEY ("friend_id") REFERENCES "users" ("id")
);