public class ECB extends OperationMode {

    public ECB(boolean encrypt, String key) {
        super(new IdeaCipher(key, encrypt), encrypt);
    }

    @Override
    protected void crypt(byte[] data, int pos) {
        idea.crypt(data, pos); // Encrypt / decrypt block
    }
}
