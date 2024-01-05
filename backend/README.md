# REST API

## Create new user

### Request

`POST /admin/users`

### BODY

    {
        name: Alex
        email: alex@gmail.com
    }

## Get users

### Request

`GET /admin/users`

### Description

    Optional parameters:
        ids : List of user ids
        from, size: If you need make pagable request 

## Delete user

### Request

`DELETE /admin/users/{userId}`

