const mongoose = require("mongoose");

const UserSchema = new mongoose.Schema(
        {
                fullName: { type: String },
                email: { type: String },
                password: { type: String },
                gender:{type:String},
                adresse:{type:String},
                role:{type:String},
                phone:{type:Number},
                DatedeNaissance:{type:String},


        },
        {
                timestamps: { currentTime: () => Date.now() },
        }
);

module.exports = mongoose.model("user", UserSchema);