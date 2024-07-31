package com.huyhieu.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.huyhieu.data.room.entity.OrderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(orderEntity: OrderEntity): Long

    @Update
    suspend fun updateOrder(orderEntity: OrderEntity): Int

    @Query("DELETE FROM `order` WHERE id IN (:orderId)")
    suspend fun deleteByOrderId(vararg orderId: Int): Int

    @Query("DELETE FROM `order`")
    suspend fun deleteAllOrders(): Int

    @Query("SELECT * FROM `order` WHERE id = :orderId")
    fun getOrder(orderId: Int): Flow<OrderEntity?>

    @Query("SELECT * FROM `order` WHERE id IN (:orderId)")
    fun getOrdersByIds(vararg orderId: Int): Flow<List<OrderEntity>?>

    @Query("SELECT * FROM `order`")
    fun getAllOrders(): Flow<List<OrderEntity>?>
}