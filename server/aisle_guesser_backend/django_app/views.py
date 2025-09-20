from django.shortcuts import render
from rest_framework.decorators import api_view
from rest_framework.response import Response

from .models import Products
from .serializers import ProductsSerializer


# Create your views here.
def product_list(request):
    all_products = Products.objects.all()

    context = {
        'products': all_products
    }

    return render(request, 'django_app/product_list.html', context)

@api_view(['GET'])
def product_detail(request):
    products = Products.objects.all()
    serializer = ProductsSerializer(products, many=True)
    return Response(serializer.data)