var express = require("express");
var bodyParser = require("body-parser");
var app = express();

app.use(bodyParser.urlencoded({extended: true}));


// const showHelloMessage = (req, res) => {
//     console.log(req.body);
//     res.send({
//         product: 'strawberries',
//         price: '5€'
//     }) 
// };

// app.get("/product/2", showHelloMessage);
// app.get("/product/1", (req, res) =>{
//     res.send({
//         product: 'apple',
//         price: '0.90€'
//     })
// })
// app.get("/product/6", (req, res) =>{
//     res.send({
//         product: 'mango',
//         price: '5.50€'
//     })
// })
// app.get("/product/7", (req, res) =>{
//     res.send({
//         product: 'tangerine',
//         price: '0.60€'
//     })
// })
// app.get("/product/8", (req, res) =>{
//     res.send({
//         product: 'cantaloupe',
//         price: '6.90€'
//     })
// })
// app.get("/product/9", (req, res) =>{
//     res.send({
//         product: 'peach',
//         price: '0.90€'
//     })
// })
// app.get("/product/3", (req, res) =>{
//     res.send({
//         product: 'orange',
//         price: '0.90€'
//     })
// })
// app.get("/product/4", (req, res) =>{
//     res.send({
//         product: 'nectarine',
//         price: '0.90€'
//     })
// })
// app.get("/product/5", (req, res) =>{
//     res.send({
//         product: 'papaya',
//         price: '8€'
//     })
// })

app.get("/product/:id", (req, res) =>{
    for(var i = 0; i<listOfFruits.length; i++){
        if(listOfFruits[i].id == req.params.id){
            res.send(
                listOfFruits[i]
            )
        }
    }
    
})
var listOfFruits = [
    {
        product: 'apple',
        id: '1',
        price: '0.90€'
    },
    {
        product: 'strawberry',
        id: '2',
        price: '5€'
    },
    {
        product: 'orange',
        id: '3',
        price: '0.90€'
    },
    {
        product: 'nectarine',
        id:'4',
        price: '0.90€'
    },
    {
        product: 'papaya',
        id: '5',
        price: '8€'
    },
    {
        product: 'mango',
        id: '6',
        price: '5.50€'
    },
    {
        product: 'tangerine',
        id: '7',
        price: '0.60€'
    },
    {
        product: 'cantaloupe',
        id: '8',
        price: '6.90€'
    },
    {
        product: 'peach',
        id: '9',
        price: '0.90€'
    }
]
app.get("/list", (req, res) =>{
    res.send({
        listOfFruits
    })
})

const port = 420;
app.listen(port, () =>{
console.log("This frutery is running on https://localhost:" + port)
});