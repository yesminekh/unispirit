const express = require("express")
var router = express.Router();
const userController = require ("..//controllers/user.controllers")



router.post("/singup",userController.singup)
router.post("/login", userController.login)
router.post("/forgotPassword", userController.forgotPassword);
router.put("/editPassword", userController.editPassword);
module.exports = router;
