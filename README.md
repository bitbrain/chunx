![ChunkX logo](chunkx.png)

Java 2D chunk engine to generate "infinite" worlds.

### How it works

To create a chunk system, simply look at the following code:

```java
ChunkTarget target = new Character(); // You have to write your own implementation for this
ConcurrentChunkSystem chunkSystem = new ConcurrentChunkSystem(4, target);
ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
executor.scheduleAtFixedRate(chunkSystem, 0, 20, TimeUnit.MILISECONDS);
chunkSystem.start();
```
To save resources (especially CPU) it is highly recommended to use Java's inbuild executor framework to handle runnables. Now you can work with the chunk system in your game code:
```java
Chunk chunk = chunkSystem.getActiveChunk();

// The system is focused and has an active chunk
if (chunk != null) {

   int indexX = chunk.getIndexX();
   int indexY = chunk.getIndexY();
   
   System.out.println("The current index is: " + indexX + "|" + indexY);
}
```
