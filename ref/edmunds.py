import os, requests, time
import mydropbox

edmunds = mydropbox.get_keys('edmunds')
api_key = edmunds['api_key']
api_secret = edmunds['api_secret']

vin = mydropbox.read_dropbox_file(os.path.join('Records', 'Financials', 'Car', 'VIN')).strip()
r = requests.get("https://api.edmunds.com/api/vehicle/v2/vins/%s?&fmt=json&api_key=%s" % (vin, api_key))
car = r.json()

time.sleep(1)

# Pulled from above query
styleid = str(car['years'][0]['styles'][0]['id'])

optionids = []
for optcat in car['options']:
    for opt in optcat['options']:
        optionids.append(str(opt['id']))

colorids = []
for colorcat in car['colors']:
    for opt in colorcat['options']:
        colorids.append(str(opt['id']))

# User-supplied
condition = "Clean"
mileage = "6000"
zipcode = "60613"

r = requests.get(
    "https://api.edmunds.com/v1/api/tmv/tmvservice/calculateusedtmv" +
    "?styleid=%s" % styleid +
    ''.join(map(lambda optionid: "&optionid=%s" % optionid, optionids)) +
    ''.join(map(lambda colorid: "&colorid=%s" % colorid, colorids)) +
    "&condition=%s" % condition +
    "&mileage=%s" % mileage +
    "&zip=%s" % zipcode +
    "&fmt=json&api_key=%s" % api_key
)

data = r.json()

totalWithOptions = data['tmv']['totalWithOptions']
disp = [
    ('Used Trade-in', 'usedTradeIn'),
    ('Used Private Party', 'usedPrivateParty'),
    ('Used TMV Retail', 'usedTmvRetail')
]

total = 0.0
for label, key in disp:
    total += totalWithOptions[key]
    print("%s: %f" % (label, totalWithOptions[key]))

total /= 3
print("Average: %f" % total)
