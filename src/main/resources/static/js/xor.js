function xor(c, k) {
    let utf8Encode = new TextEncoder();
    let kBytes = utf8Encode.encode(k);
    let cBytes = utf8Encode.encode(c);
    for (let i = 0; i < cBytes.length; i++) {
        for (const byte of kBytes) {
            cBytes[i] = cBytes[i] ^ byte;
        }
    }
    let utf8Decoder = new TextDecoder()
    return utf8Decoder.decode(cBytes)
}