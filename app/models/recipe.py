from sqlalchemy import Column, Integer, String, ForeignKey
from sqlalchemy.orm import relationship

from app.db.base_class import Base


class Recipe(Base):
    id = Column(Integer, primary_key=True, index=True)
    name = Column(String(256), nullable=False)
    source = Column(String(256), nullable=True)
    url = Column(String(256), index=True, nullable=True)
    prep_time = Column(Integer, nullable=False)
    wait_time = Column(Integer, nullable=False)
    cook_time = Column(Integer, nullable=False)
    servings = Column(Integer, nullable=False)
    calories = Column(Integer, nullable=False)
    instructions = Column(String(256), nullable=False)
    ingredients = Column(String(256), nullable=False)
