package contracts
import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return all vegetarian recipes"

    request {
        method GET()
        urlPath("/api/recipes") {
            queryParameters {
                parameter("vegetarian", "true")
                parameter("page", "1")
                parameter("size", "5")
                parameter("sort", "title,DESC"); // column, ASC|DESC
            }
        }
    }

    response {
        status 200
        body([
                [
                        id          : "1",
                        title       : "Vegetarian Pizza",
                        instructions: "Bake the pizza.",
                        servings    : 2,
                        vegetarian  : true,
                        ingredients : [
                                [name: "Cheese"],
                                [name: "Tomato"]
                        ]
                ]
        ])
        headers {
            contentType(applicationJson())
        }
    }
}

