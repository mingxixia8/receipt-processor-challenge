<h1>Receipt Processor Challenge</h1>
Receipt Processor Challenge is a RESTful API developed using Java17 and Spring Boot 3.0.6. It mainly provides two functions: processing receipts and calculating points, and obtaining points by receipt ID.

<h2>Development Requirements</h2>

<li>Docker(must)</li>
<li>Postman(optional)</li>

<h2>Project Architecture</h2>
<li>src/main/java/com/FetchRewards/receiptprocessorchallenge/controller/ReceiptController.java` - The control layer defines the APIs.</li>
<li>src/main/java/com/FetchRewards/receiptprocessorchallenge/model/` - Data model layer, including `Receipt` and `Item` two entity classes.</li>
<li>src/main/java/com/FetchRewards/receiptprocessorchallenge/service/ReceiptService.java` - Service layer, responsible for processing the calculation of points.</li>
<li>src/test/java/com/FetchRewards/receiptprocessorchallenge/` - Test layer, contains unit tests for control layer and service layer.</li>

<h2>Endpoints</h2>
The application provides the following endpoints:

<li>POST /receipts/process: Accepts a receipt in JSON format and returns a JSON object containing a unique ID for the receipt. The receipt should be in the following format:</li>

```
{
    "retailer": "Retailer Name",
    "purchaseDate": "yyyy-mm-dd",
    "purchaseTime": "hh:mm",
    "items": [
        {
            "shortDescription": "Item Description",
            "price": 0.01
        }
    ],
    "total": 0.01
}
```
<li>GET /receipts/{id}/points: Returns a JSON object containing the number of points awarded for the receipt with the provided ID.</li>



<h2>Build&Run</h2>
<h3>Using Docker to build and run</h3>
1.Clone the repository:

```
git clone https://github.com/mingxixia8/receipt-processor-challenge.git
```

2.Change into the directory:

```
cd receipt-processor-challenge
```
3.Using Docker to build the program:(make sure you started the docker desktop)

```
docker build -t receipt-processor-challenge .
```

4.Run docker container:

Note: before run this command, make sure your 8080 port is available.

```
docker run -p 8080:8080 receipt-processor-challenge
```

5.Now you can start to test apis


<h2>Test APIs </h2>

There's two ways to test apis by yourself: using `curl` or Postman

<h3>Using `curl` command in your terminal</h3>

Open a new terminal window and cd to `~/receipt-processor-challenge`

```
curl -X POST -H "Content-Type: application/json" -d 
'{
  "retailer": "Target",
  "purchaseDate": "2022-01-01",
  "purchaseTime": "13:01",
  "items": [
    {
      "shortDescription": "Mountain Dew 12PK",
      "price": "6.49"
    },{
      "shortDescription": "Emils Cheese Pizza",
      "price": "12.25"
    },{
      "shortDescription": "Knorr Creamy Chicken",
      "price": "1.26"
    },{
      "shortDescription": "Doritos Nacho Cheese",
      "price": "3.35"
    },{
      "shortDescription": "   Klarbrunn 12-PK 12 FL OZ  ",
      "price": "12.00"
    }
  ],
  "total": "35.35"
}' http://localhost:8080/receipts/process
```

Then you will get an `{id:..}` in the terminal, copy the id to replace `{id}` in the following command:

```
curl -X GET http://localhost:8080/receipts/{id}/points
```

<h3>Using Postman (if you installed already)</h3>
Open Postman and click `+` to create a new HTTP request


1.Choose `POST` and enter `http://localhost:8080/receipts/process` in URL box,
Then click `Body` -> `raw` -> `JSON`, and input:

```
{
  "retailer": "Target",
  "purchaseDate": "2022-01-01",
  "purchaseTime": "13:01",
  "items": [
    {
      "shortDescription": "Mountain Dew 12PK",
      "price": "6.49"
    },{
      "shortDescription": "Emils Cheese Pizza",
      "price": "12.25"
    },{
      "shortDescription": "Knorr Creamy Chicken",
      "price": "1.26"
    },{
      "shortDescription": "Doritos Nacho Cheese",
      "price": "3.35"
    },{
      "shortDescription": "   Klarbrunn 12-PK 12 FL OZ  ",
      "price": "12.00"
    }
  ],
  "total": "35.35"
}
```

Or you can input your customized text with the same JSON format.

Then click `Send` , you will get `{id:......}` in the Response.

2.Creat a new request using `+`, then choose `GET` and enter `http://localhost:8080/receipts/{id}/points` in URL,
copy the id that you got from `POST` response and replace `{id}` in the URL, then click `Send`.

For example:
`http://localhost:8080/receipts/82e5abd4-5f44-4c0b-abb2-2d173f546bcd/points`





