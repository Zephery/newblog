import requests
from aip import AipOcr


def get_file_content(filePath):
    with open(filePath, 'rb') as fp:
        return fp.read()


def recognition_word(url):
    APP_ID = '10530811'
    API_KEY = 'LwqnDLTHYIdH3H5pOIM5H3wB'
    SECRET_KEY = 'EIa2DECI9udOOK3acNrg3mxsIGcT7nDK'
    client = AipOcr(APP_ID, API_KEY, SECRET_KEY)
    aaa = requests.get(url).content
    with open("1.jpg", "wb") as fp:
        fp.write(aaa)
    result = client.basicGeneral(aaa)
    print(result)
    return result['words_result'][0]['words']


if __name__ == '__main__':
    code = recognition_word("http://login.sina.com.cn/cgi/pin.php")
    print(code)
