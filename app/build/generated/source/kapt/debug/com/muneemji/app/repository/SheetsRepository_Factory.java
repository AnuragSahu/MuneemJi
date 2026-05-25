package com.muneemji.app.repository;

import android.content.Context;
import com.muneemji.app.db.TransactionDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class SheetsRepository_Factory implements Factory<SheetsRepository> {
  private final Provider<Context> contextProvider;

  private final Provider<TransactionDao> transactionDaoProvider;

  public SheetsRepository_Factory(Provider<Context> contextProvider,
      Provider<TransactionDao> transactionDaoProvider) {
    this.contextProvider = contextProvider;
    this.transactionDaoProvider = transactionDaoProvider;
  }

  @Override
  public SheetsRepository get() {
    return newInstance(contextProvider.get(), transactionDaoProvider.get());
  }

  public static SheetsRepository_Factory create(Provider<Context> contextProvider,
      Provider<TransactionDao> transactionDaoProvider) {
    return new SheetsRepository_Factory(contextProvider, transactionDaoProvider);
  }

  public static SheetsRepository newInstance(Context context, TransactionDao transactionDao) {
    return new SheetsRepository(context, transactionDao);
  }
}
