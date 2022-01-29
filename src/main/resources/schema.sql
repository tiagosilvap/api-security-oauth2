SET
SQL_MODE='ALLOW_INVALID_DATES';

drop table if exists oauth_client_details;
create table oauth_client_details
(
    client_id               VARCHAR(255) PRIMARY KEY,
    resource_ids            VARCHAR(255),
    client_secret           VARCHAR(255),
    scope                   VARCHAR(255),
    authorized_grant_types  VARCHAR(255),
    web_server_redirect_uri VARCHAR(255),
    authorities             VARCHAR(255),
    access_token_validity   INTEGER,
    refresh_token_validity  INTEGER,
    additional_information  VARCHAR(4096),
    autoapprove             VARCHAR(255),
    UNIQUE INDEX ix_client_id (client_id)
);

drop table if exists oauth_client_token;
create table oauth_client_token
(
    token_id          VARCHAR(255),
    token             MEDIUMBLOB,
    authentication_id VARCHAR(255) PRIMARY KEY,
    user_name         VARCHAR(255),
    client_id         VARCHAR(255)
);

drop table if exists oauth_access_token;
create table oauth_access_token
(
    token_id          VARCHAR(255),
    token             MEDIUMBLOB,
    authentication_id VARCHAR(255) PRIMARY KEY,
    user_name         VARCHAR(255),
    client_id         VARCHAR(255),
    authentication    MEDIUMBLOB,
    refresh_token     VARCHAR(255),
    create_date       DATETIME DEFAULT NOW(),
    INDEX             ix_client_id (client_id),
    INDEX             ix_create_date_access_token (create_date),
    UNIQUE INDEX ix_token_id (token_id),
    UNIQUE INDEX ix_refresh_token (refresh_token)
);

drop table if exists oauth_refresh_token;
create table oauth_refresh_token
(
    token_id       VARCHAR(255),
    token          MEDIUMBLOB,
    authentication MEDIUMBLOB,
    create_date    DATETIME DEFAULT NOW(),
    INDEX          ix_create_date_refresh_token (create_date),
    UNIQUE INDEX ix_refresh_token_id (token_id)
);

drop table if exists oauth_code;
create table oauth_code
(
    code           VARCHAR(256),
    authentication MEDIUMBLOB
);

drop table if exists oauth_approvals;
create table oauth_approvals
(
    userId         VARCHAR(256),
    clientId       VARCHAR(256),
    scope          VARCHAR(256),
    status         VARCHAR(10),
    expiresAt      TIMESTAMP,
    lastModifiedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

INSERT INTO oauth_client_details (client_id,
                                  resource_ids,
                                  client_secret,
                                  scope,
                                  authorized_grant_types,
                                  web_server_redirect_uri,
                                  authorities,
                                  access_token_validity,
                                  refresh_token_validity,
                                  additional_information,
                                  autoapprove)
VALUES ('3dd0e341-d18b-42d8-b981-a701dc3f62c1',
        'oauth2-resource',
        '$2a$10$rqwmirn0uwP.65QJ0j.1nOoWvvukCqv47mzaSktGhmlisrGXEqiQO',
        'read,write',
        'client_credentials,password,refresh_token',
        NULL,
        'ADMIN',
        600,
        900,
        '{}',
        NULL);
