db.createUser({
  user: "my_admin_user",
  pwd: "my_secure_password",
  roles: [ { role: "root", db: "admin" } ]
})