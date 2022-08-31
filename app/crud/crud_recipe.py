from app.crud.base import CRUDBase
from app.models.recipe import Recipe
from app.schemas.recipe import RecipeCreate, RecipeUpdate


class CRUDRecipe(CRUDBase[Recipe, RecipeCreate, RecipeUpdate]):
    pass


recipe = CRUDRecipe(Recipe)
