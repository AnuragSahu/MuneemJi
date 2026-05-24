package com.muneemji.app.repository;

import android.content.Context;
import com.muneemji.app.db.TransactionDao;
import com.muneemji.app.parser.TransactionParser;
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
public final class SmsRepository_Factory implements Factory<SmsRepository> {
  private final Provider<Context> contextProvider;

  private final Provider<TransactionDao> transactionDaoProvider;

  private final Provider<TransactionParser> parserProvider;

  public SmsRepository_Factory(Provider<Context> contextProvider,
      Provider<TransactionDao> transactionDaoProvider, Provider<TransactionParser> parserProvider) {
    this.contextProvider = contextProvider;
    this.transactionDaoProvider = transactionDaoProvider;
    this.parserProvider = parserProvider;
  }

  @Override
  public SmsRepository get() {
    return newInstance(contextProvider.get(), transactionDaoProvider.get(), parserProvider.get());
  }

  public static SmsRepository_Factory create(Provider<Context> contextProvider,
      Provider<TransactionDao> transactionDaoProvider, Provider<TransactionParser> parserProvider) {
    return new SmsRepository_Factory(contextProvider, transactionDaoProvider, parserProvider);
  }

  public static SmsRepository newInstance(Context context, TransactionDao transactionDao,
      TransactionParser parser) {
    return new SmsRepository(context, transactionDao, parser);
  }
}
