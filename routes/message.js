const express = require("express")
var router = express.Router();
const messageController = require ("../controllers/message.controllers")



router.post("/sendMessage", messageController.sendMessage)
router.get("/getall", messageController.getAllMessage)
module.exports = router;
