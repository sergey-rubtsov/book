package contracts
import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should update an existing recipe"

    request {
        method PUT()
        url "/api/recipes"
        body([
                title       : "Potato Salad",
                instructions: "Some updated instruction",
                servings    : 3,
                ingredients : [
                        [name: "Cheese", "category": "VEGETARIAN"],
                        [name: "Tomato", "category": "VEGETARIAN"]
                ]
        ])
        headers {
            contentType(applicationJson())
        }
    }

    response {
        status 200
        body([
                title       : "Potato Salad",
                instructions: "Some updated instruction",
                servings    : 3,
                ingredients : [
                        [name: "Cheese", "category": "VEGETARIAN"],
                        [name: "Tomato", "category": "VEGETARIAN"]
                ]
        ])
        headers {
            contentType(applicationJson())
        }
    }
}


