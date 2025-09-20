from django.db import models

# Create your models here.

class Products(models.Model):
    product_id = models.AutoField(primary_key=True)
    product_name = models.CharField(max_length=200)
    product_aisle_number = models.CharField(max_length=50)
    product_type = models.CharField(max_length=100)
    product_type_hint = models.CharField(max_length=200, blank=True, null=True)
    product_category = models.CharField(max_length=200)
    product_spoof_of = models.CharField(max_length=200, blank=True, null=True)
    game_difficulty = models.CharField(max_length=50)
    additional_notes = models.TextField(max_length=500, blank=True, null=True)

    class Meta:
        verbose_name = "Product"
        verbose_name_plural = "Products"

    def __str__(self):
        return self.product_name