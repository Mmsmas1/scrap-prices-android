# Rebuild the RSA signing key from prime p (base64) and the public certificate.
import sys,base64,subprocess,re
def der_int(v):
    b=v.to_bytes((v.bit_length()+8)//8,'big')
    return b'\x02'+der_len(len(b))+b
def der_len(n):
    if n<128: return bytes([n])
    b=n.to_bytes((n.bit_length()+7)//8,'big'); return bytes([0x80|len(b)])+b
def seq(b): return b'\x30'+der_len(len(b))+b
p=int.from_bytes(base64.b64decode(open(sys.argv[1]).read().strip()),'big')
t=subprocess.check_output(['openssl','x509','-in',sys.argv[2],'-noout','-modulus']).decode()
n=int(t.split('=')[1],16); e=65537
assert n%p==0, 'p does not match certificate'
q=n//p; d=pow(e,-1,(p-1)*(q-1)//__import__('math').gcd(p-1,q-1))
dp=d%(p-1); dq=d%(q-1); qi=pow(q,-1,p)
rsa=seq(b''.join(der_int(x) for x in (0,n,e,d,p,q,dp,dq,qi)))
alg=bytes.fromhex('300d06092a864886f70d0101010500')
pk8=seq(der_int(0)+alg+b'\x04'+der_len(len(rsa))+rsa)
open(sys.argv[3],'wb').write(pk8)
