db.auth('user', 'pass')

db = db.getSiblingDB('bankAccount')

db.createUser({
  user: "appuser",
  pwd: "apppass",
  roles: [{role: "readWrite", db: "bankAccount"}]
});
