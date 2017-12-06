public abstract class BlockCipher {

    private int keySize;
    private int blockSize;

    BlockCipher(int keySize, int blockSize) {
        this.keySize = keySize;
        this.blockSize = blockSize;
    }
        /**
     * Set the key from a block of bytes.
     */
    protected abstract void setKey(byte[] key);

    /**
     * Set the key from a string.
     *
     */
    protected void setKey(String charKey) {
        setKey(Utils.makeKey(charKey, keySize));
    }

    /**
     * Encrypts / decrypts a 64-bit block of data.
     *
     */
    public abstract void crypt(byte[] data, int offset);
}