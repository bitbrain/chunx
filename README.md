![ChunkX logo](chunx.png)

Java 2D chunk engine to generate "infinite" worlds.

### How it works

To create a chunk system, simply look at the following code:

```java
ChunkTarget target = new Character(); // You have to write your own implementation
ContentProvider provider = new World(); // You have to write your own implementation
CachedChunkConfiguration  configuration = new CachedChunkConfiguration();
configuration.setFocused(target);
configuration.setContentProvider(provider);
configuration.setChunkIndex(4);

ChunkSystem chunkSystem = new ConcurrentChunkSystem(new SimpleCachedChunkSystem(configuration));
chunkSystem.start();
```
Now you can work with the chunk system in your game code:
```java
Chunk chunk = chunkSystem.getActiveChunk();

// The system is focused and has an active chunk
if (chunk != null) {

   int indexX = chunk.getIndexX();
   int indexY = chunk.getIndexY();
   
   System.out.println("The current index is: " + indexX + "|" + indexY);
}
```

### Getting started

Read [the official wiki](https://github.com/MyRealityCoding/chunx/wiki) for more information.

### Download

You can download the newest bundle [here](https://www.dropbox.com/sh/h5eixlxtj5wkfya/gxacHRVHcc).

### Credits

[chunx](https://github.com/MyRealityCoding/chunx) has been developed by [Miguel Gonzalez](http://my-reality.de).
