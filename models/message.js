const mongoose = require("mongoose");

const MessageSchema = new mongoose.Schema(
        {
              sarahaMessage:{type:String},


        },
        {
                timestamps: { currentTime: () => Date.now() },
        }
);

module.exports = mongoose.model("message", MessageSchema);