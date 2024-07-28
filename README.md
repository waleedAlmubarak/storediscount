
# Store Discounts Application

The Store Discounts Application is designed to manage discounts for various types of customers in a store. The application includes different discount strategies such as employee discounts, affiliate discounts, and loyal customer discounts.




## Run Locally

Clone the project

```bash
  git clone https://github.com/waleedAlmubarak/storediscount.git
```

Go to the project directory

```bash
  cd storediscounts
```

Install dependencies

```bash
  mvn clean install
```



## Running Tests

To run tests, run the following command

```bash
  mvn test
```

When executing this command successfully, you will get:

**Code Coverage**

Go to ```target/site/jacoco``` directory. Open the `index.html` file in a web browser to view the coverage report.


![Screenshot of a comment on a GitHub issue showing an image, added in the Markdown, of an Octocat smiling and raising a tentacle.](https://raw.githubusercontent.com/waleedAlmubarak/storediscount/main/src/main/resources/assets/coverage.png)

**Static Code Analysis**

To run static code analysis tests, run the following command

```bash
  mvn site
```

Go to ```target/site``` directory. Open the `checkstyle.html` file in a web browser to view code analysis report.


![Screenshot of a comment on a GitHub issue showing an image, added in the Markdown, of an Octocat smiling and raising a tentacle.](https://raw.githubusercontent.com/waleedAlmubarak/storediscount/main/src/main/resources/assets/checkstyles.png)



## License

[MIT](https://choosealicense.com/licenses/mit/)

