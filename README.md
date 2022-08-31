# Foodimizer
Project for IT class

RDS Database that contains recipes for food items.
## Executing
#### Powershell

```poetry run powershell.exe ./run.ps1```

#### Bash
```poetry run ./run.sh```
```
Database should be formatted as such

{
    "Key": Name
    "Recipe":
    {
        "Name": str
        "Source": str
        "Preptiime" : int
        "WaitTime" : int
        "CookTime" : int
        "Servings" : int
        "Calories" : int
        "Instructions" : srt
        "ingredients" :
        {
            "Name" : "str"
            "Quantity" "float"
        },
    }
}
```
## Contributing

