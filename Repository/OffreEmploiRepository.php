<?php

namespace App\Repository;

use App\Entity\OffreEmploi;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method OffreEmploi|null find($id, $lockMode = null, $lockVersion = null)
 * @method OffreEmploi|null findOneBy(array $criteria, array $orderBy = null)
 * @method OffreEmploi[]    findAll()
 * @method OffreEmploi[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class OffreEmploiRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, OffreEmploi::class);
    }

    // /**
    //  * @return OffreEmploi[] Returns an array of OffreEmploi objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('o')
            ->andWhere('o.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('o.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */
    public function findByASC()
    {
        return $this->createQueryBuilder('s')
            ->orderBy('s.Salaire', 'ASC')
            ->getQuery()
            ->getResult()
            ;
    }
   public function findByClasse($value)
    {
        return $this->createQueryBuilder('s')
            ->andWhere('s.Entreprise. = :val')
            ->setParameter('val',$value)
            ->getQuery()
            ->getResult()
            ;
    }
    public function countForStage(string $type)
    {
        return $this->createQueryBuilder('s')
            ->Where('s.Type = :type')
            ->setParameter('type', $type)
            ->select('count(s.id)')
            ->getQuery()
            ->getSingleScalarResult();
    }
    /*
    public function findOneBySomeField($value): ?OffreEmploi
    {
        return $this->createQueryBuilder('o')
            ->andWhere('o.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
