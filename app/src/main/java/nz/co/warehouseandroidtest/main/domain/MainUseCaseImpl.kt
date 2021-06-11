package nz.co.warehouseandroidtest.main.domain

import nz.co.warehouseandroidtest.main.data.repository.MainRepository
import javax.inject.Inject

class MainUseCaseImpl @Inject constructor(private val mMainRepository: MainRepository) :
    MainUseCase {
    override suspend fun getUserId() =  mMainRepository.getUserId()


}
