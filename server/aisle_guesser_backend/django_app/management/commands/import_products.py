import csv
from django.core.management.base import BaseCommand
from django_app.models import Products

class Command(BaseCommand):
    help = 'Import products from csv file'

    def add_arguments(self, parser):
        parser.add_argument('csv_file', type=str, help = "The path to the csv file")

    def handle(self, *args, **options):
        file_path = options['csv_file']

        try:
            with open(file_path, 'r', encoding='utf-8-sig') as file:

                reader = csv.DictReader(file)
                for row in reader:
                    Products.objects.create(
                        product_id = row['product_id'],
                        product_name = row['product_name'],
                        product_aisle_number = row['product_aisle_number'],
                        product_type = row['product_type'],
                        product_type_hint = row.get('product_type_hint', ''),
                        product_category = row['product_category'],
                        product_spoof_of = row.get('product_spoof_of', ''),
                        game_difficulty = row['game_difficulty'],
                        additional_notes = row.get('additional_notes', '')
                    )
            self.stdout.write(self.style.SUCCESS('Successfully imported product data'))
        except FileNotFoundError:
            self.stdout.write(self.style.ERROR(f'File not found: {file_path}'))
        except Exception as e:
            self.stdout.write(self.style.ERROR(f'An unexpected error occurred: {e}'))