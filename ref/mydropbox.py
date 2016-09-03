"""
Get API keys from my Dropbox store.

"""
import os, platform, json, configparser

def get_dropbox_folder():
    s = platform.system()

    if s in ['Linux', 'Darwin']:
        opt = os.path.join(os.path.expanduser('~'), '.dropbox', 'info.json')
        with open(opt, 'r') as f:
            j = json.loads(f.read())
            return j['personal']['path']
    elif s in ['Windows']:
        opt1 = os.path.join(os.getenv('APPDATA'), 'Dropbox', 'info.json')
        opt2 = os.path.join(os.getenv('LOCALAPPDATA'), 'Dropbox', 'info.json')
        opt = opt1 if os.path.isfile(opt1) else opt2

        with open(opt, 'r') as f:
            j = json.loads(f.read())
            return j['personal']['path']
    else:
        raise NotImplementedError

# http://stackoverflow.com/questions/2819696/parsing-properties-file-in-python/2819788#2819788
def add_section_header(f):
    yield '[section]\n'
    for line in f:
        yield line

def get_keys(kn):
    pf = os.path.join(get_dropbox_folder(), 'Records', 'API', kn)
    config = configparser.ConfigParser()
    config.read_file(add_section_header(open(pf)), source=pf)
    return dict(config['section'])

def read_dropbox_file(rp):
    pf = os.path.join(get_dropbox_folder(), rp)
    with open(pf) as f:
        return f.read()
