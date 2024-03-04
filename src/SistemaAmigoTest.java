package src;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class SistemaAmigoTest {

    @Test
    void testSortear() {
        // Initialize your SistemaAmigo instance and amigoList with test data
        SistemaAmigo sistemaAmigo = new SistemaAmigo();
        sistemaAmigo.cadastraAmigo("Amigo1", "amigo1@example.com");
        sistemaAmigo.cadastraAmigo("Amigo2", "amigo2@example.com");
        sistemaAmigo.cadastraAmigo("Amigo3", "amigo3@example.com"); // Odd number of participants

        // Call the sortear method
        sistemaAmigo.sortear();

        // Retrieve the updated amigoList
        List<Amigo> updatedAmigos = sistemaAmigo.getAmigoList();

        // Assert that the list is empty after sorting, indicating all amigos have been sorted
        assertTrue(updatedAmigos.isEmpty(), "The list should be empty after sorting");

        // For demonstration purposes, you might also want to verify the secret friend assignments
        // However, since the amigoList is now empty, you would need to adjust your implementation
        // to store or return the updated amigos with their secret friend assignments.
    }
}

