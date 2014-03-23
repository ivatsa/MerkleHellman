import java.math.BigInteger;
import java.util.Random;
import java.lang.Math;


public class MerkleHellmanKnapsack {

	BigIntegerDoublyLinkedList w = new BigIntegerDoublyLinkedList();
	BigIntegerDoublyLinkedList b = new BigIntegerDoublyLinkedList();
	BigInteger q, r;
	
	public byte[] decrypt(BigInteger encryptedMessage) {
		BigInteger modinv = new BigInteger("0");
		BigInteger highValue = new BigInteger("0");
		BigInteger zero = new BigInteger("0");
		BigInteger b = encryptedMessage.mod(q);
		modinv = r.modInverse(q);
		highValue = b.multiply(modinv);
		highValue = highValue.mod(q);
		
		byte[] result = new byte[w.countNodes()];
		int inc=0;
		int i = w.countNodes();
		while (!highValue.equals(zero)){
				if(w.get(i-1).data.compareTo(highValue)<=0){
					highValue = highValue.subtract(w.get(i-1).data);
					byte newMask = 1;
					if (i%8 != 0){
						inc = 0;
						newMask = (byte) (1L << (8-(i%8)));
					}
					else
						inc = 1;
					result[(i/8)-inc] = (byte) (result[(i/8)-inc] | newMask);
				}
				i--;
		}
		System.out.println("Result: "+new String(result));
		return result;
	}

	public BigInteger encrypt(byte[] inputBytes) {
		if (inputBytes.length <= 80) {
			generate(inputBytes);
			 BigInteger encryptedMessage = new BigInteger("0");

		        // The input size is persisted in the object so that decryption functionality 
		        // knows the input size

		        // Iterate through the input data and create the encrypted number. 
		        // If a particular bit of the input bytes is 1, the corresponding index 
		        // in the public key (b) will be added to the encryped number
		        int i = 0;
		        while(i<inputBytes.length){
		            byte inputByte = inputBytes[i];
		            for (int j = 0; j < 8; j++) {
		                BigInteger bv = b.get(i * 8 - j + 7).data;
		                if ((inputByte & (1L << j)) != 0) {
		                    encryptedMessage = encryptedMessage.add(bv);
		                }
		            }
		            i++;
		        }
		        
		        return encryptedMessage;
		}
		return null;
		
	}
	
		public void generate(byte[] inputBytes) {
			//System.out.println("IN GENERATE");
			for (int i = 0; i < inputBytes.length * 8; i++) {
				BigInteger total = sequence();
				w.addCharAtEnd(total); 
			}
			q = sequence();
			r = new BigInteger(32 - Integer.numberOfLeadingZeros(q.intValue()), new Random());
			while (q.gcd(r).intValue() != 1) {
				r = r.nextProbablePrime();
			}
			BigIntegerDoubleNode node = w.head;
			while (node != null) {
				b.addCharAtEnd(((node.data).multiply(r)).mod(q));
				node = node.next;
			}
		}
		
	 public BigInteger sequence() {
		 BigInteger total = new BigInteger("0");
			BigIntegerDoubleNode node = w.head;
			while (node != null) {
				total = total.add(node.data);
				node=node.next;
			}
			return total.nextProbablePrime();
	 }
	}
