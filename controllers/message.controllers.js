const Message = require("../models/message");
exports.sendMessage=async(req,res)=>{
    const{sarahaMessage}= req.body;
    const newMessage = new Message()
    newMessage.sarahaMessage=sarahaMessage;
    newMessage.save();
    console.log(newMessage);
    res.status(200).send({message:"success", message:newMessage})



}

exports.getAllMessage = async (req, res) => {
    const Messages = await Message.find();
if(Messages){
        res.status(200).send(Messages );}
        else 
        {
            res.status(403).send({ message: "empty" });
        }
};