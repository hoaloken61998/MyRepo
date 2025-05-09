const express = require('express')
const app = express()
const port = 3002

const morgan = require('morgan')
app.use(morgan('combined'))

const bodyParser = require('body-parser')
app.use(bodyParser.json())
app.use(bodyParser.urlencoded({ extended: true }))

const cors = require("cors")
app.use(cors())

app.listen(port, () => {
    console.log(`Example app listening at http://localhost:${port}`)
})

app.get('/', (req, res) => {
    res.send("This Web server is processed for MongoDB")
})

const { MongoClient, ObjectID } = require('mongodb')
client = new MongoClient('mongodb://localhost:27017')
client.connect()
database = client.db('FashionData')
fashionCollection = database.collection('Fashion')

app.get("/fashions", cors(), async (req, res) => {
    const result = await fashionCollection.find({}).toArray();
    res.send(result)
}
)