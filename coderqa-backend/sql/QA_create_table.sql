-- 數據庫初始化
-- database創建
create database if not exists coderqa;

-- 切換database
use coderqa;

-- 用戶表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userAccount  varchar(256)                           not null comment '帳號',
    userPassword varchar(512)                           not null comment '密碼',
    unionId      varchar(256)                           null comment '微信開放平台id',
    mpOpenId     varchar(256)                           null comment '公眾號openId',
    userName     varchar(256)                           null comment '用戶暱稱',
    userAvatar   varchar(1024)                          null comment '用戶頭像',
    userProfile  varchar(512)                           null comment '用戶簡介',
    userRole     varchar(256) default 'user'            not null comment '用戶角色：user/admin/vip/ban',
    vipExpireTime datetime     null comment '會員過期時間',
    vipCode       varchar(128) null comment '會員兌換碼',
    vipNumber     bigint       null comment '會員編號',
    editTime     datetime     default CURRENT_TIMESTAMP not null comment '編輯時間',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '創建時間',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新時間',
    isDelete     tinyint      default 0                 not null comment '是否刪除至回收站',
    index idx_unionId (unionId)
    ) comment '用戶' collate = utf8mb4_unicode_ci;

-- 題庫表
create table if not exists question_bank
(
    id          bigint auto_increment comment 'id' primary key,
    title       varchar(256)                       null comment '標題',
    description text                               null comment '描述',
    picture     varchar(2048)                      null comment '圖片',
    userId      bigint                             not null comment '創建用戶 id',
    editTime    datetime default CURRENT_TIMESTAMP not null comment '編輯時間',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '創建時間',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新時間',
    isDelete    tinyint  default 0                 not null comment '是否刪除',
    index idx_title (title)
    ) comment '題庫' collate = utf8mb4_unicode_ci;

-- 題目表
create table if not exists question
(
    id         bigint auto_increment comment 'id' primary key,
    title      varchar(256)                       null comment '標題',
    content    text                               null comment '內容',
    tags       varchar(1024)                      null comment '標籤列表（json 陣列）',
    answer     text                               null comment '推薦答案',
    userId     bigint                             not null comment '創建用戶 id',
    editTime   datetime default CURRENT_TIMESTAMP not null comment '編輯時間',
    createTime datetime default CURRENT_TIMESTAMP not null comment '創建時間',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新時間',
    isDelete   tinyint  default 0                 not null comment '是否刪除',
    index idx_title (title),
    index idx_userId (userId)
    ) comment '題目' collate = utf8mb4_unicode_ci;

-- 題庫題目表（硬刪除）
create table if not exists question_bank_question
(
    id             bigint auto_increment comment 'id' primary key,
    questionBankId bigint                             not null comment '題庫 id',
    questionId     bigint                             not null comment '題目 id',
    userId         bigint                             not null comment '創建用戶 id',
    createTime     datetime default CURRENT_TIMESTAMP not null comment '創建時間',
    updateTime     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新時間',
    UNIQUE (questionBankId, questionId)
    ) comment '題庫題目' collate = utf8mb4_unicode_ci;