from pydantic import BaseModel, HttpUrl

from typing import Sequence


class RecipeBase(BaseModel):
    name: str
    source: str
    url: HttpUrl
    prep_time: int
    wait_time: int
    cook_time: int
    servings: int
    calories: int
    instructions: str
    ingredients: dict


class RecipeCreate(RecipeBase):
    name: str
    source: str
    url: HttpUrl
    prep_time: int
    wait_time: int
    cook_time: int
    servings: int
    calories: int
    instructions: str
    ingredients: dict


class RecipeUpdate(RecipeBase):
    name: str


# Properties shared by models stored in DB
class RecipeInDBBase(RecipeBase):
    id: int

    class Config:
        orm_mode = True


# Properties to return to client
class Recipe(RecipeInDBBase):
    pass


# Properties properties stored in DB
class RecipeInDB(RecipeInDBBase):
    pass


class RecipeSearchResults(BaseModel):
    results: Sequence[Recipe]
