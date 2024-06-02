# Avoiding duplicates when enriching a knowledge graph

`CREATE`

- `CREATE` always creates new data

`MERGE`

- `MERGE` inserts records only if the entirety of supplied pattern does not already exist
- `MERGE` behaves like `CREATE` when no matching data exists in the database
- Its semenatics are a mix of `MATCH` and `CREATE`, it will either match whole patterns or create new records that match the pattern in its entirety

`CONSTRAINT`

- this can help better support `MERGE`

# Neo4j Internals

A Graph database mainly performs two functions on behalf of its users

- Queries graph data performantly
- Stores graph data safely

`Query Processing`

- To query knowledge graphs performantly, graph database must make traversals (act of moving from one node across a relationship to another node) very fast (or low latency) and cheap (for high concurrent throughput)

- Neo4j stores structure of a graph (nodes and relationships) separately from the property data.

- The graph structure is stored as fixed-length records: one store for nodes and a similar one for relationships. Multiplying the ID of a record by its size in bytes gives you its offset in corresponding store file.

- Makes use of index-free adjacency
