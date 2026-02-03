# E-Commerce Microservice Application

Microservices that can be designed as part of a large e-commerce application:
1. Product Catalog Service
2. Inventory Service
3. Order Service

## Product Catalog Service

### Overview

Responsible for managing product catalog data, which is used by customers and other services.

Reponsibilities:
- Manage Product Catalog
- Manage Product Catalog Categories
- Pricing Information
- Manage Product life cycle (ACTIVE/DISCONTINUED)
- Search Filters

### Domain Model
 
**Product**: Entity representing a product
```code
Product
--------------
id (UUID)
sku (string, unique)
name (string) 
description (string)
price (decimal)
status (ProductStatus)
category (UUID)
brand (string)
createdAt (instant)
updatedAt (instant)
```

**Product Status**: Enum
```code
ACTIVE
INACTIVE
DISCONTINUED
```

**Category**: Entity representing a product category. This supports hierarchial structure
```code
id (UUID)
name (string)
parentCategory (UUID)
createdAt (instant)
updatedAt (instant)
```

### Repository

**ProductRepository**
```code
save
findById
findBySku
findByProductStatus
findByCategory
searchNameContainsString
existsBySku
```

**CategoryRepository**
```code
save
findById
findByParentCategory
findByName

```


## Inventory Service

Service that manages the stock level of each product. Responsibilities:
- Manage stock avaialbility
- Reserve and Release stock
- Stock updates after purchase
- Low stock alerts

## Order Service

Manages the orders placed by the users. Responsibilities:
- Create Order
- Manage Order Status (CREATED, PLACED, SHIPPED, DELIVERED, CANCELLED)
- Manager Order history
- Coordinate with inventory, payment and shipping services
