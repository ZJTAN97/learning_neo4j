# Retrieving Nodes

`Node Retrieval`

```cypher

MATCH
(p:Person {name: 'Tom Hanks'})-[:ACTED_IN]->(m:Movie {title: 'Cloud Atlas'})
RETURN p, m

```

`WHERE` filter

- adds more logic

```cypher

MATCH(p:Person)
WHERE p.name = 'Tom Hanks' OR p.name = 'Rita Wilson'
RETURN p.name, p.born

```

---

# Finding Relationships

Find movies the actor acted in

```cypher

MATCH(p:Person {name: 'Tom Hanks'})-[:ACTED_IN]->(m: Movie)
return m.title

```

Find the number of directors for a movie "Cloud Atlas"

```cypher

MATCH(p: Person)-[:DIRECTED]->(:Movie {title: "Cloud Atlas"})
RETURN count(p)

```

---

# Filtering Queries

Essentially using `WHERE` clause

```cypher

MATCH (p)-[:ACTED_IN]->(m)
WHERE p:Person AND m:Movie AND m.title = 'The Matrix'
return p.name

```

Check whether property exists

```cypher

MATCH (p:Person)-[:ACTED_IN]->(m:Movie)
WHERE p.name = "Jack Nicholson" AND m.tagline IS NOT NULL
RETURN m.title

```

Partial String Matching

- `STARTS WITH`

Testing in a list of values

```cypher

MATCH (p:Person)
WHERE p.born IN [1965, 1970, 1975]
RETURN p.name

```

Testing to an existing list in graph

```cypher

MATCH (p:Person)-[r:ACTED_IN]->(m:Movie)
WHERE  'Neo' IN r.roles AND m.title='The Matrix'
RETURN p.name, r.roles

```

Discover keys for a Node

```cypher

MATCH (p:Person)
RETURN p.name, keys(p)

```

Find all property keys defined in the graph

```

CALL db.propertyKeys()

```
